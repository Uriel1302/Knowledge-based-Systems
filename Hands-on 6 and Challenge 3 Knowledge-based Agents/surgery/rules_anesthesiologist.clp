
(defrule r2(patient anesthesize)
   =>
   (printout t "El paciente está anestesiado..." crlf)(assert(patient anesthetized)))

