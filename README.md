# Convert AFND to AFD

![](image/imageAFND.png)


# AFND to AFD Conversion

This project implements the conversion of a Non-deterministic Finite Automaton (AFND) to a Deterministic Finite Automaton (AFD).

## Introduction
Finite automata are used in various fields of computer science, including text processing, compiler construction, and more. A Non-deterministic Finite Automaton (AFND) allows multiple transitions for a given state and input symbol, whereas a Deterministic Finite Automaton (AFD) allows only one transition per state and input symbol. This project demonstrates the conversion process from AFND to AFD.

## Algorithm
The conversion process follows these steps:

1. **Initial State**: The initial state of the AFD is the set containing the initial state of the AFND.
2. **State Sets**: Each set of states in the AFND will be treated as a single state in the AFD.
3. **Transition Function**: For each set of states \( S \) and each symbol \( a \) in the alphabet:
   - Determine the set of states that can be reached from any state in \( S \) with the symbol \( a \), considering epsilon transitions if present.
   - This new set of states will represent a state in the AFD.
4. **Accepting States**: A state in the AFD is an accepting state if it contains at least one accepting state of the original AFND.
   

## Example
Here is an example of an AFND and its corresponding AFD:

### AFND
```
- States: {q0, q1, q2}
- Alphabet: {a, b}
- Transition function:
  - δ(q0, a) = {q0, q1}
  - δ(q0, b) = {q0}
  - δ(q1, b) = {q2}
  - δ(q2, a) = {q2}
- Initial state: q0
- Accepting states: {q2}
```

### AFD
```
- States: { {q0}, {q0, q1}, {q0, q2}, {q0, q1, q2} }
- Alphabet: {a, b}
- Transition function:
  - δ({q0}, a) = {q0, q1}
  - δ({q0}, b) = {q0}
  - δ({q0, q1}, a) = {q0, q1}
  - δ({q0, q1}, b) = {q0, q2}
  - δ({q0, q2}, a) = {q0, q2}
  - δ({q0, q2}, b) = {q0}
  - δ({q0, q1, q2}, a) = {q0, q1, q2}
  - δ({q0, q1, q2}, b) = {q0, q2}
- Initial state: {q0}
- Accepting states: { {q0, q2}, {q0, q1, q2} }
```

## Dependencies
- Java 11 or higher
- Maven 3.6 or higher

