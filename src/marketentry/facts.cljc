(ns marketentry.facts
  "Jamaica market-entry catalog.

  Every fact under \"JAM\" below is grounded ONLY in a verified research
  dossier gathered by web search against `.gov.jm` government domains
  (ppc.gov.jm, procureja.gov.jm). No regulatory claim, requirement, or
  institutional detail beyond that dossier is stated here -- the same
  discipline `marketentry.governor`'s `spec-basis-violations` enforces
  downstream.

    - Procurement regulator: PPC -- Public Procurement Commission.
      https://ppc.gov.jm/ . Registration/licensing page:
      https://ppc.gov.jm/suppliers-registration-and-licensing/ .
    - Related oversight body: OCG -- Office of the Contractor General,
      operating under the Contractor General Act, monitors the award
      and implementation of Government of Jamaica (GOJ) contracts to
      ensure impartiality/merit-based awards without impropriety, and
      can formally investigate matters associated with contract
      awards, licence/permit issuance, procurement procedures, and
      contractor registration. NOT itself a registration gate this
      actor checks (it is an oversight/investigative body, not a
      pre-tender qualification requirement) -- included for context
      only, not encoded as a governor check.
    - Legal basis: The Public Procurement Act, 2015, plus three
      related documents flowing from the Act (the dossier did not
      surface concrete titles/citations for those three documents, so
      they are not individually named here).
    - Registration threshold (the flagship rule this catalog grounds):
      a contractor or supplier who wishes to tender on a Government
      contract valued at J$1,500,000.00 or above MUST first be
      registered with the PPC to be qualified to submit a valid tender
      or be awarded a Government contract.
    - PPC registration eligibility criteria: firms must (a) be
      registered with the Companies Office of Jamaica, or the
      equivalent companies registry in their country of origin if
      foreign, (b) be tax compliant, (c) receive authorization from
      relevant governing/regulatory bodies applicable to their sector,
      and (d) demonstrate the capacity to supply the goods/services/
      works to government entities.
    - Related government procurement policy portal:
      https://procureja.gov.jm/ (Office of Public Procurement Policy)
      hosts a \"PPC Registration\" knowledge-base page.
    - Business/commercial registration authority: Companies Office of
      Jamaica.

  What this catalog deliberately does NOT claim: the dossier did not
  independently confirm a specific transactional e-procurement portal
  URL/name beyond `procureja.gov.jm`/`ppc.gov.jm` themselves --
  `:national-spec` names those two sites, not a distinct e-tendering
  system name. Do not add one."
  )

(def registration-threshold-jmd
  "J$1,500,000.00 -- a contractor/supplier tendering on a Government of
  Jamaica contract valued at or above this amount MUST first be
  registered with the PPC. This is DEMO/DOCUMENTATION grounding for
  `marketentry.store`'s demo engagements (which record their own
  `:tender-value-jmd` alongside the ground-truth
  `:requires-ppc-registration?` flag an engagement's own intake data
  declares) -- it is not independently recomputed by the governor,
  which (same architecture as every sibling actor's flagship check)
  trusts the engagement's own `:requires-ppc-registration?` ground
  truth and independently verifies only `:has-ppc-registration?`."
  1500000)

(def catalog
  {"JAM" {:name "Jamaica"
          :owner-authority "Public Procurement Commission (PPC)"
          :legal-basis "The Public Procurement Act, 2015 -- a contractor or supplier who wishes to tender on a Government of Jamaica contract valued at J$1,500,000.00 or above MUST first be registered with the PPC to be qualified to submit a valid tender or be awarded a Government contract"
          :national-spec "PPC registration via ppc.gov.jm / procureja.gov.jm -- no independently verified distinct transactional e-procurement portal name beyond these two sites"
          :provenance "https://ppc.gov.jm/"
          :required-evidence ["Companies Office of Jamaica registration record (or equivalent foreign companies-registry record, if foreign)"
                               "Tax compliance record"
                               "Sector-specific regulatory/governing-body authorization record"
                               "PPC registration record (required to tender on or be awarded a Government of Jamaica contract valued at J$1,500,000.00 or above)"]
          ;; flagship-check grounding (PPC registration threshold)
          :rep-owner-authority "Public Procurement Commission (PPC)"
          :rep-legal-basis "The Public Procurement Act, 2015 registration-threshold rule: tendering on a GOJ contract valued at J$1,500,000.00 or above requires prior PPC registration"
          :rep-provenance "https://ppc.gov.jm/suppliers-registration-and-licensing/"
          ;; corporate-number-equivalent-check grounding (Companies Office of Jamaica registration)
          :corporate-number-owner-authority "Companies Office of Jamaica"
          :corporate-number-legal-basis "PPC registration eligibility criterion (a): firms must be registered with the Companies Office of Jamaica, or the equivalent companies registry in their country of origin if foreign"
          :corporate-number-provenance "https://procureja.gov.jm/"
          ;; context only -- NOT a governor check, see namespace docstring
          :oversight-authority "Office of the Contractor General (OCG)"
          :oversight-note "Operates under the Contractor General Act; monitors GOJ contract awards/implementation and may investigate contract awards, licence/permit issuance, procurement procedures, and contractor registration. An oversight/investigative body, not itself a pre-tender qualification gate."
          :oversight-provenance "https://ppc.gov.jm/"}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
