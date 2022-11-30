
(defrule r1(patient ready)
   =>
   (printout t "El paciente esta anestesiado" crlf)(retract 1)(assert(patient anesthetize) )

