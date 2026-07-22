# cloud-itonami-iso3166-jam

**`:implemented`** for **JAM** (Jamaica): an "Actors" pattern market-entry /
public-procurement compliance service (Governor + LLM advisor + langgraph-clj
StateGraph + append-only audit ledger + Store), adapted from the
`cloud-itonami-iso3166-ago` reference implementation.

Flagship check: `ppc-registration-missing` (Public Procurement Commission
registration, required above the Public Procurement Act, 2015's
J$1,500,000.00 GOJ-tender-value threshold). Corporate-number-equivalent
check: `companies-office-registration-unverified` (Companies Office of
Jamaica registration). **Seven** governor checks, same shape as the AGO
reference — see `src/marketentry/governor.cljc`.

```
clojure -M:dev:test   # run the full test suite
clojure -M:lint       # clj-kondo, errors fail
clojure -M:dev:run    # demo driver (marketentry.sim)
```

## Correction: this repo previously claimed "GOJEP / e-GP" and "TRN"

This repo's original blueprint stub said Jamaica's procurement channel was
**"GOJEP / e-GP public procurement portal"** and that Companies Office of
Jamaica registration paired with **"TRN"**. Neither claim was independently
verified against `.gov.jm` government domains during this actor's build, so
neither is repeated in `src/marketentry/facts.cljc`, `organization.edn`, or
`docs/`. What IS verified, and is what this actor's catalog now cites:

- **Public Procurement Commission (PPC)** — the public-procurement
  regulator/registrar. `https://ppc.gov.jm/`, registration/licensing page
  `https://ppc.gov.jm/suppliers-registration-and-licensing/`.
- **Registration threshold** — a contractor or supplier who wishes to
  tender on a Government of Jamaica contract valued at **J$1,500,000.00 or
  above** MUST first be registered with the PPC.
- **PPC registration eligibility criteria**: (a) registered with the
  Companies Office of Jamaica (or the equivalent foreign registry), (b) tax
  compliant, (c) authorized by relevant sector governing/regulatory bodies,
  (d) demonstrated capacity to supply.
- **Legal basis** — The Public Procurement Act, 2015.
- **Office of the Contractor General (OCG)** — related oversight body
  (Contractor General Act); monitors GOJ contract awards/implementation and
  may investigate procurement matters. Named for context only — NOT encoded
  as a governor check (it is oversight/investigative, not a pre-tender
  qualification gate).
- **Office of Public Procurement Policy** — `https://procureja.gov.jm/`,
  hosts a "PPC Registration" knowledge-base page.
- **National e-procurement** — no independently confirmed distinct
  transactional e-procurement portal name beyond `ppc.gov.jm`/
  `procureja.gov.jm` themselves; not invented.

See `docs/business-model.md` for the Trust Controls this actor enforces and
`docs/operator-guide.md` for the human-operator workflow.

AGPL-3.0-or-later.

## Culture catalog

This repo carries a **country-level regional-culture catalog**
(ADR-2607171400 addendum 2, `cloud-itonami-municipality-culture-catalog`
Wave 1, in `com-junkawasaki/root`) — national dishes, protected products,
beverages, crafts, festivals and heritage sites for Jamaica:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring the `statute.facts` convention of the
  iso3166 siblings).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
