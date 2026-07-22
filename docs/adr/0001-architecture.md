# ADR-0001: JAM

Mirrors `cloud-itonami-iso3166-ago`'s architecture (langgraph-clj
StateGraph OperationActor, sealed MarketEntry-LLM advisor, independent
Market-Entry Compliance Governor, Store protocol with MemStore +
DatomicStore, 0→3 phase rollout gate, append-only audit ledger), with
Jamaica-specific field names and governor checks in place of Angola's:

- `ppc-registration-missing` (flagship jurisdiction-specific check) in
  place of `ao-entity-missing`. Field names:
  `:requires-ppc-registration?` / `:has-ppc-registration?` in place of
  `:requires-ao-entity?` / `:has-ao-entity?`. Grounded in the Public
  Procurement Act, 2015's explicit numeric rule: a contractor or
  supplier who wishes to tender on a Government of Jamaica contract
  valued at J$1,500,000.00 or above must first be registered with the
  Public Procurement Commission (PPC). The demo engagement set
  (`marketentry.store/demo-data`) includes both an above-threshold
  unregistered case (`eng-4`, fires the violation) and a
  below-threshold case (`eng-6`, correctly does NOT require
  registration) to prove the threshold is applied in both directions,
  not just the failure direction.
- `companies-office-registration-unverified`
  (corporate-number/tax-ID-equivalent check) in place of
  `nif-unverified`. Field names:
  `:requires-companies-office-registration?` /
  `:companies-office-registration-verified?` in place of
  `:requires-nif?` / `:nif-verified?`. Grounded in PPC registration
  eligibility criterion (a): firms must be registered with the
  Companies Office of Jamaica (or the equivalent companies registry in
  their country of origin if foreign).

`marketentry.store`'s `DatomicStore` is built directly on
`io.github.kotoba-lang/langchain-store` (`ls/enc`/`ls/dec*`/
`ls/identity-schema`/`ls/read-stream`/`ls/append-blob!`) from the
start, rather than a hand-rolled `enc`/`dec*` codec (ADR-2607141600: ~190
cloud-itonami actors hand-roll that exact two-liner; new stores must
not add to that count).

Every fact in `src/marketentry/facts.cljc` is grounded in the verified
Jamaica research dossier only — the Public Procurement Commission
(PPC, `ppc.gov.jm`), the Office of Public Procurement Policy
(`procureja.gov.jm`), the Public Procurement Act, 2015, the
J$1,500,000.00 registration threshold, the PPC registration
eligibility criteria, the Companies Office of Jamaica, and the Office
of the Contractor General (named for oversight context only, not
encoded as a governor check — it is an investigative body under the
Contractor General Act, not a pre-tender qualification gate). No
distinct transactional e-procurement portal name is claimed beyond
`ppc.gov.jm`/`procureja.gov.jm` themselves.
