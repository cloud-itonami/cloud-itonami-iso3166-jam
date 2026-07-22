# Operator guide — JAM

Human-gated filing only. Every `:filing/draft`/`:filing/submit`
proposal always pauses for a human market-entry operator's approval —
there is no rollout phase in which either auto-commits (see
`src/marketentry/phase.cljc`).

## Portal / channel

**PPC** (Public Procurement Commission) — `https://ppc.gov.jm/` —
registers and licenses suppliers/contractors
(`https://ppc.gov.jm/suppliers-registration-and-licensing/`); a
contractor or supplier who wishes to tender on a Government of Jamaica
contract valued at **J$1,500,000.00 or above MUST first be registered
with the PPC**. The Office of Public Procurement Policy portal
`https://procureja.gov.jm/` also hosts a "PPC Registration"
knowledge-base page. There is no independently verified distinct
transactional e-procurement portal name beyond these two sites — this
actor tracks registration evidence, it does not submit through a
portal API that was not confirmed to exist.

## Workflow

1. `:engagement/intake` — record the operator, portal, and fee terms
   for a new engagement. May auto-commit at phase 3 once the governor
   is clean (low real-world risk — no filing has happened yet).
2. `:jurisdiction/assess` — request the JAM evidence checklist. ALWAYS
   requires human approval, even when clean (governor `escalate?` is
   forced true for this operation regardless of phase).
3. `:filing/draft` — prepare the internal filing-draft record.
   Requires a completed assessment on file (`evidence-incomplete`
   otherwise). ALWAYS requires human approval.
4. `:filing/submit` — prepare the filing-submit record, the step that
   corresponds to an actual real-world PPC-facing action. ALWAYS
   requires human approval, and is independently checked against:
   - `ppc-registration-missing` — when the engagement's GOJ tender
     value put it at/above the J$1,500,000.00 PPC-registration
     threshold (`:requires-ppc-registration? true`), is PPC
     registration actually on file (`:has-ppc-registration?`)? A
     tender **below** the threshold correctly does NOT trigger this
     check.
   - `engagement-fee-mismatch` — does the claimed fee actually equal
     `base-fee + monthly-rate × monitoring-months`?
   - `companies-office-registration-unverified` — has Companies
     Office of Jamaica registration itself actually been independently
     verified, when the engagement declares verification is required
     (PPC registration eligibility criterion (a))?
   - `already-drafted` / `already-submitted` — refuses to double-file
     the same engagement.

Any HARD violation above is a hold NO approver can override — the
operator must fix the underlying engagement record (confirm PPC
registration, correct the fee, verify Companies Office registration)
before resubmitting, not approve past the governor.

## Required evidence checklist (per `src/marketentry/facts.cljc`)

- Companies Office of Jamaica registration record (or equivalent
  foreign companies-registry record, if foreign)
- Tax compliance record
- Sector-specific regulatory/governing-body authorization record
- PPC registration record (required to tender on or be awarded a GOJ
  contract valued at J$1,500,000.00 or above)

## What this actor does NOT claim

The Office of the Contractor General (OCG) is a real oversight body
(Contractor General Act) that monitors GOJ contract awards and can
investigate procurement matters, but the dossier does not establish it
as a pre-tender registration gate — this guide does not treat OCG
clearance as a checklist item, and neither does the governor. If you
find a verifiable source that changes this, extend
`src/marketentry/facts.cljc`'s `catalog` — do not hand-edit a claim
into this guide or any other doc without an official source.
