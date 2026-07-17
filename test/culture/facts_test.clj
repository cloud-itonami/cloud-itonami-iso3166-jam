(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest jam-has-culture-basis
  (let [sb (facts/spec-basis "JAM")]
    (is (= 8 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "JAM" (:culture/country %)) sb))
    (is (every? #(nil? (:culture/municipality %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-jurisdiction-has-no-basis
  (is (nil? (facts/spec-basis "CUB")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["JAM" "CUB"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["CUB"] (:missing-jurisdictions c)))))

(deftest by-kind-filters
  (is (= 4 (count (facts/by-kind "JAM" :dish))))
  (is (= ["jam.festival.reggae-sumfest"]
         (mapv :culture/id (facts/by-kind "JAM" :festival))))
  (is (empty? (facts/by-kind "JAM" :other)))
  (is (empty? (facts/by-kind "CUB" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
