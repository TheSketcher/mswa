# Dokumentation der Designentscheidungen nach dem ADR Modell

## Template:

## Fortlaufenden Nummer

### Überschrift

Im Kontext von <use case/user story u>, sind wir Problem <concern/c> begegnet.
Wir haben uns für <option/o> entschieden, um <quality/q> zu erreichen, und haben dabei <downside/d> akzeptiert.

## Entscheidung 1.0

### Threading zur Performanceverbesserung / Designentscheidung Johannes Wittwer

Im Kontext der Nutzung der verschiedenen Schnittstellen, sind wir dem Problem der langsamen Antwortzeut der Schnittstellen begegnet. Wir haben uns für Threading entschieden um dadurch alle Schnittstellen gleichzeitg abzufragen und die Antwortzeiten zu verbessern. Dabei haben wir die Downside akzeptiert, dass die Programmierung etwas aufwendiger ist. Unsere Entscheidung ist auf die Qualität der Performance ausgelegt, wie auch definiert in unseren Hauptzielen.
