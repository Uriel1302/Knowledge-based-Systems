(defrule r1(patient ready) => (printout t"La enfermera ya trajo al paciente y será pasado a anestesia" crlf)(assert(patient anesthesize)))