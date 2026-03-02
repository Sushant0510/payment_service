# SOLID Improvement Suggestions

This document highlights concrete places in the current code where you can improve adherence to SOLID principles.

## 1) Single Responsibility Principle (SRP)

- `PaymentService` currently handles payment persistence, hash orchestration, gateway request preparation, and HTML form generation. Form generation has now been extracted into `PaymentFormBuilder` / `PayUAutoSubmitFormBuilder`.
- `PaymentCallbackService` still performs multiple concerns in one flow:
  - callback interpretation,
  - payment state update,
  - event payload construction,
  - outbox entity creation.

**Suggested next extraction:**
- `PaymentStatusResolver` (maps callback payload -> domain status).
- `PaymentEventFactory` (creates `PaymentEvent`).
- `OutboxEventFactory` (creates `OutboxEvent`).

## 2) Open/Closed Principle (OCP)

- `PayUAutoSubmitFormBuilder` introduces extension by allowing alternate form/redirect builders without changing `PaymentService`.
- `PaymentCallbackService` uses string literals (`"success"`, `"PAYMENT_SUCCESS"`, `"NEW"`) inline.

**Suggested next change:**
- Use constants/enums or strategy objects for callback handling (`SuccessCallbackHandler`, `FailureCallbackHandler`) so new callback states can be added by extension.

## 3) Liskov Substitution Principle (LSP)

- DTO/entity classes are straightforward and don't currently violate substitution explicitly.

**Suggested check:**
- If you introduce interfaces for gateways or handlers, ensure implementations preserve method contracts (e.g., no implementation that returns `null` where non-null is expected).

## 4) Interface Segregation Principle (ISP)

- Repositories are already narrowly focused.

**Suggested next change:**
- If service interfaces are introduced, keep them task-oriented (e.g., `PaymentInitiationService`, `PaymentCallbackProcessor`) rather than one large service interface.

## 5) Dependency Inversion Principle (DIP)

- `PaymentService` now depends on `PaymentFormBuilder` abstraction for form generation.

**Suggested next change:**
- Introduce interfaces around callback side effects:
  - `PaymentEventPublisher` (current outbox pattern implementation as one adapter),
  - `Clock` injection for deterministic timestamps in tests,
  - `IdGenerator` abstraction for UUID generation.

## High-Impact Refactor Sequence

1. Extract event/outbox factories from `PaymentCallbackService`.
2. Introduce `CallbackProcessor` strategies by status.
3. Add unit tests for each strategy/factory.
4. Replace runtime string literals with enums/constants.
