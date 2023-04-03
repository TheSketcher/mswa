# Dokumentation der Designentscheidungen nach dem ADR Modell

Im Kontext von der Entwicklung einer Anwendung welche eine Dokumentendruckschnittstelle sowie API-Schnittstellen "Fahrzeugentwicklung, Logistik" und eine selbst entwickelte Verwaltungsschnittstelle enthält, sind wir verschiedenen Problemen begegnet.
Die Entwicklung einer solchen Anwendung erfordert eine sorgfältige Planung und Implementierung, um sicherzustellen, dass alle Komponenten nahtlos miteinander interagieren und die gewünschten Ergebnisse liefern. Während des Entwicklungsprozesses können jedoch verschiedene Probleme auftreten, die die erfolgreiche Umsetzung der Anwendung gefährden können.
Einige dieser Probleme können technischer Natur sein, wie beispielsweise Konflikte bei der Integration von APIs oder Probleme bei der Datenübertragung zwischen den verschiedenen Komponenten der Anwendung. Andere Herausforderungen können jedoch auch organisatorischer oder prozessualer Natur sein, wie z.B. Probleme bei der Zusammenarbeit zwischen verschiedenen Teams oder Schwierigkeiten bei der Erstellung und Aktualisierung der Dokumentation für die Anwendung. Um diese Herausforderungen zu bewältigen und eine erfolgreiche Implementierung zu gewährleisten, ist es wichtig, geeignete Prozesse und Tools zu implementieren, die eine effektive Zusammenarbeit ermöglichen und die Qualität und Zuverlässigkeit der Anwendung sicherstellen.

# Inhaltsverzeichnis

<!-- vscode-markdown-toc -->

- 1. [Entscheidung 1.0](#Entscheidung1.0)
  - 1.1. [Threading zur Performanceverbesserung / Designentscheidung Johannes Wittwer](#ThreadingzurPerformanceverbesserungDesignentscheidungJohannesWittwer)
  - 1.2. [Einheitliche & Standardisierte Struktur des Projektes durch die Verwendung von Maven / Christopher Caspari](#EinheitlicheStandardisierteStrukturdesProjektesdurchdieVerwendungvonMavenChristopherCaspari)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

## 1. <a name='Entscheidung1.0'></a>Entscheidung 1.0

### 1.1. <a name='ThreadingzurPerformanceverbesserungDesignentscheidungJohannesWittwer'></a>Threading zur Performanceverbesserung / Designentscheidung Johannes Wittwer

Im Kontext der Nutzung der verschiedenen Schnittstellen, sind wir dem Problem der langsamen Antwortzeut der Schnittstellen begegnet. Wir haben uns für Threading entschieden um dadurch alle Schnittstellen gleichzeitg abzufragen und die Antwortzeiten zu verbessern. Dabei haben wir die Downside akzeptiert, dass die Programmierung etwas aufwendiger ist. Unsere Entscheidung ist auf die Qualität der Performance ausgelegt, wie auch definiert in unseren Hauptzielen.

### 1.2. <a name='EinheitlicheStandardisierteStrukturdesProjektesdurchdieVerwendungvonMavenChristopherCaspari'></a>Einheitliche & Standardisierte Struktur des Projektes durch die Verwendung von Maven / Christopher Caspari

Durch die Verwendung von Maven wird die Projektstruktur automatisch erstellt und die Verzeichnisse werden entsprechend der Standard-Konventionen benannt. Dadurch kann eine bessere Lesbarkeit und Wartbarkeit des Codes und die Zusammenarbeit mit anderen Entwicklern erleichtert werden. Maven kann darüber hinaus auch genutzt werden, um Abhängigkeiten zu verwalten anhand einer "pom.xml"-Datei welche die einzelnen Abhängigkeiten gliedert, Build-Prozesse automatisieren und ausführbare Artefakte erstellen. Das kann dazu beitragen, die Effizienz und Qualität der Entwicklung zu verbessern.

[def]: #fortlaufenden-nummer
