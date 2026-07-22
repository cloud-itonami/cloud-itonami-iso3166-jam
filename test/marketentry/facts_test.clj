(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest jam-has-spec-basis
  (let [sb (facts/spec-basis "JAM")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/rep-spec-basis "JAM")))
    (is (some? (facts/corporate-number-spec-basis "JAM")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "JAM")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "JAM" all)))
    (is (not (facts/required-evidence-satisfied? "JAM" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["JAM" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "USA"] (:missing-jurisdictions c)))))

(deftest national-spec-does-not-invent-a-transactional-portal
  (testing "the dossier only grounds ppc.gov.jm/procureja.gov.jm -- do not claim a distinct e-tendering system name for JAM"
    (let [sb (facts/spec-basis "JAM")]
      (is (re-find #"ppc\.gov\.jm" (:national-spec sb)))
      (is (re-find #"procureja\.gov\.jm" (:national-spec sb)))
      (is (re-find #"(?i)no independently verified" (:national-spec sb))
          "must explicitly disclaim a distinct transactional portal name, not just omit one"))))

(deftest registration-threshold-is-the-dossiers-exact-figure
  (is (= 1500000 facts/registration-threshold-jmd)))
