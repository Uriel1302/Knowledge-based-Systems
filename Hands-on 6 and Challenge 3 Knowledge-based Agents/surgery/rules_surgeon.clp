(defrule r1(patient anesthetized) => 
(printout t"El cirujano empezara la cirugia..." crlf)(assert(patient surgery)))