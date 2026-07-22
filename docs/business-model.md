# Business model — JAM

Independent Public-Sector Market-Entry & Procurement Compliance Service
for Jamaica: an assistive actor that helps a market-entry operator
assemble, track, and (with a human's sign-off) file the registration
evidence a foreign or domestic operator needs to bid on Government of
Jamaica (GOJ) public-sector contracts.

## Who this serves

Companies (and their local counsel/agents) preparing to bid on GOJ
public-sector contracts, who need to track:

- **Public Procurement Commission (PPC) registration** —
  `https://ppc.gov.jm/` — a contractor or supplier who wishes to
  tender on a GOJ contract valued at **J$1,500,000.00 or above MUST
  first be registered with the PPC** to be qualified to submit a valid
  tender or be awarded a Government contract. The registration/
  licensing page is
  `https://ppc.gov.jm/suppliers-registration-and-licensing/`, and the
  Office of Public Procurement Policy portal `https://procureja.gov.jm/`
  hosts a "PPC Registration" knowledge-base page.
- **Companies Office of Jamaica registration** — one of the PPC
  registration eligibility criteria: firms must be registered with the
  Companies Office of Jamaica, or the equivalent companies registry in
  their country of origin if foreign.
- **Tax compliance** and **sector-specific regulatory/governing-body
  authorization** — the other two PPC registration eligibility
  criteria this actor's evidence checklist tracks.
- **Office of the Contractor General (OCG)** oversight — operating
  under the Contractor General Act, the OCG monitors the award and
  implementation of GOJ contracts and can formally investigate matters
  associated with contract awards, licence/permit issuance,
  procurement procedures, and contractor registration. This is
  context, not a check this actor performs (see Trust Controls below).

## What this actor does

1. **Engagement intake** — normalize the operator's own case data
   (operator name, engagement fee terms). No new facts invented.
2. **Jurisdiction assessment** — hand back the JAM evidence checklist
   from `src/marketentry/facts.cljc`, always citing an official source
   (`ppc.gov.jm`, `procureja.gov.jm`). A jurisdiction not in the
   catalog gets NO checklist — the actor states plainly that it has no
   official spec-basis rather than guessing.
3. **Filing draft** — prepare the FILING-DRAFT record (an unsigned,
   internal book-of-record entry — not a real PPC submission).
4. **Filing submit** — prepare the FILING-SUBMIT record. This is the
   step that corresponds to an actual real-world PPC-facing action, so
   it is the most tightly gated.

## Trust Controls

- **A false or fabricated regulatory-requirement claim is a HARD
  hold.** Every jurisdiction assessment must cite an official source
  from `marketentry.facts`; an assessment with no citation, or one that
  claims a `:spec-basis` this actor never verified, is rejected outright
  and no human can override it.
- **Any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off.** `:filing/draft` and `:filing/submit` proposals are
  NEVER auto-committed, at any rollout phase — a human market-entry
  operator makes the actual filing decision every time, even when the
  governor finds nothing wrong.
- **Independent re-verification, not trust-on-claim.** The governor
  independently recomputes the claimed engagement fee (`base-fee +
  monthly-rate × monitoring-months`) rather than trusting the claimed
  total, and independently checks the engagement's own
  `:has-ppc-registration?` / `:companies-office-registration-verified?`
  facts rather than assuming a filing-draft or filing-submit proposal
  is accurate.
- **The J$1,500,000.00 PPC-registration threshold is applied exactly
  as stated, not rounded or generalized.** An engagement whose GOJ
  tender value is below that figure genuinely does not require PPC
  registration — this actor's demo engagement set (`eng-6` in
  `marketentry.store`) deliberately includes a below-threshold case to
  prove the flagship check does not fire a false positive there, next
  to `eng-4`'s above-threshold, unregistered case that correctly does
  fire.
- **No invented transactional e-procurement portal.** The dossier
  documents `ppc.gov.jm` and `procureja.gov.jm` as the PPC's and the
  Office of Public Procurement Policy's own sites, respectively, but
  did not independently confirm a distinct named transactional
  e-tendering system — this actor does not claim one.
- **The Office of the Contractor General is named for context, not
  encoded as a check.** The OCG is an oversight/investigative body
  under the Contractor General Act, not a pre-tender qualification
  gate this actor's engagements pass through — conflating the two
  would be inventing a requirement the dossier does not state.
- **Append-only audit ledger.** Every commit or hold decision writes
  exactly one immutable ledger fact — there is a complete, tamper-evident
  record of what was proposed, what the governor found, and what a human
  approved or rejected.

## Regulatory sources (all independently verified against `.gov.jm` domains)

- Public Procurement Commission (PPC): `https://ppc.gov.jm/`
- PPC suppliers registration/licensing:
  `https://ppc.gov.jm/suppliers-registration-and-licensing/`
- Office of Public Procurement Policy: `https://procureja.gov.jm/`
- Legal basis: The Public Procurement Act, 2015
- Office of the Contractor General (oversight, Contractor General Act)
- Companies Office of Jamaica (business/commercial registration
  authority)
