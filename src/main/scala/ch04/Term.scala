package ch04

enum Term {
  case True
  case False
  case If(term1: Term, term2: Term, term3: Term)
  case Zero
  case Succ(term: Term)
  case Pred(term: Term)
  case IsZero(term: Term)
}
