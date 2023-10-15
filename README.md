# HiSrc BasicJAXB Annox

Parse XML Schema to find Java annotation declarations.

## Description

This repository is a fork of [Annox][21]. The original project was developed by
the admirable Alexey Valikov (a.k.a. [Highsource][2]). This repository contains
Java projects to build Maven artifact(s) related to the Java Architecture for
XML Binding (JAXB) framework. It is one of a family of repositories forked from
[Highsource][2] that provide tools for JAXB and JPA processing. Repo and
artifact names have been changed to reflect the familial connection between the
repositories and to fix a conformance issue with the original
`maven-jaxb2-plugin` name.

### List of repositories in this family

| Patrodyne                   | Highsource                  | Purpose                                                |
| --------------------------- | --------------------------- | ------------------------------------------------------ |
| [hisrc-basicjaxb-annox][11] | [annox][21]                 | Parse XML Schema to find Java annotation declarations. |
| [hisrc-basicjaxb][12]       | [jaxb2-basics][22]          | A library of XJC plugins and tools to extend JAXB.     |
| [hisrc-higherjaxb][13]      | [maven-jaxb2-plugin][23]    | Maven plugin to generated Java source from XML Schema. |
| [hisrc-hyperjaxb-annox][14] | [jaxb2-annotate-plugin][24] | XJC plugin to add arbitrary Java annotations to JAXB.  |
| [hisrc-hyperjaxb][15]       | [hyperjaxb3][25]            | Maven and XJC plugins to add JPA annotations to JAXB.  |

### Graph of repository relationships

![Patrodyne-Highsource Graph][1]

### Releases

#### GitHub Releases, Demonstrations

* [HiSrc BasicJAXB Annox v2.1.1, Samples][32]
* [HiSrc BasicJAXB Annox v2.1.0, Samples][31]

#### Maven Repositories

* Search
	* [MVN Repository](https://mvnrepository.com/artifact/org.patrodyne.jvnet?sort=popular)
	* [Central Repository](https://central.sonatype.com/search?q=org.patrodyne.jvnet+hisrc-basicjaxb-annox&sort=name)
* Index
	* [Central Repository](https://repo1.maven.org/maven2/org/patrodyne/jvnet/)

#### JavaDoc

* [hisrc-basicjaxb-annox-parser][40]

### Goals

New goals for the next release are:

* Review in progress.

### Status

* Review in progress.

#### Completed

* Review in progress.

### Fork History

#### Version 2.1.1

* Improved [`annox.xsd`][3] for `*.ann.xml` instances.
* Read JVM properties from `main` or `test`.
* Added system property: [`xml.catalog.verbosity`][4].
* Update Maven plugin and dependency versions.
* Resolved deprecated method(s).

#### Version 2.1.0

* Clean up Java compiler _lint_ warnings.
* Recheck Dependency Management for newer versions.
* Configure menu, log and Maven options from build-CFG.sh.
* Include JVM system arguments from `src/test/resources/jvmsystem.arguments`.
* Moved 'samples' folder to new 'assembly' folder and restored int. testing.
* Compile sources and generate classes for Java release v11 using JDK 17.
* Replaced `maven.compiler.source/target="11"` with `maven.compiler.release="11"`.
* Update JAXB dependencies from 3.x to 4.x.

#### Version 2.0.0

* Update dependencies with newer versions *including* the Jakarta namespace.
* Replace _eclipse-only_ lifecyle profile with _m2e_ XML directive.
* Convert DOS line endings to Unix newlines.
* Update JUnit v4 to JUnit v5.
* Rename packages:
    * BasicJAXB Annotations
        * `OLD: org.jvnet.annox`
        * `NEW: org.jvnet.basicjaxb_annox`
    * BasicJAXB XJC Plugin
        * `OLD: org.jvnet.jaxb2_commons`
        * `NEW: org.jvnet.basicjaxb`
    * HigherJAXB Maven Plugin
        * `OLD: org.jvnet.mjiip`
        * `NEW: org.jvnet.higherjaxb`
    * HigherJAXB Maven Mojo
        * `OLD: org.jvnet.jaxb2.maven2`
        * `NEW: org.jvnet.higherjaxb.mojo`
    * HyperJAXB Persistence
        * `OLD: org.jvnet.hyperjaxb[23]`
        * `NEW: org.jvnet.hyperjaxb`
* Rename namespaces:
    * BasicJAXB XJC Annotations
        * `OLD: http://annox.dev.java.net`
        * `NEW: http://jvnet.org/basicjaxb/xjc/annox`
    * BasicJAXB XJC Plugin
        * `OLD: http://jaxb2-commons.dev.java.net/basic`
        * `NEW: http://jvnet.org/basicjaxb/xjc`
    * HyperJAXB Persistence
        * `OLD: http://hyperjaxb3.jvnet.org/ejb/schemas/customizations`
        * `NEW: http://jvnet.org/hyperjaxb/jpa`
* Update version to 2.0.0 due to Jakarta EE and other name changes.

#### Version 1.0.4

* Update dependencies with newer versions *excluding* the Jakarta namespace.

#### Version 1.0.3

* Obsolete build scripts have been removed.
* New build scripts have been added.
* POMs have been refactored with renamed artifacts.
* POMs have been updated to reduce warnings and errors.
* Configured SLF4J with SimpleLogger as the log framework.
* Changes to Java sources to resolve warnings/errors.
* Verification of unit and integration tests.

<!-- References -->

  [1]: https://raw.githubusercontent.com/patrodyne/hisrc-hyperjaxb/master/etc/hisrc-repositories.svg
  [2]: https://github.com/highsource
  [3]: https://github.com/patrodyne/hisrc-basicjaxb-annox/blob/master/core/src/main/resources/annox.xsd
  [4]: https://xerces.apache.org/xml-commons/components/resolver/resolver-article.html
  [11]: https://github.com/patrodyne/hisrc-basicjaxb-annox#readme
  [12]: https://github.com/patrodyne/hisrc-basicjaxb#readme
  [13]: https://github.com/patrodyne/hisrc-higherjaxb#readme
  [14]: https://github.com/patrodyne/hisrc-hyperjaxb-annox#readme
  [15]: https://github.com/patrodyne/hisrc-hyperjaxb#readme
  [21]: https://github.com/highsource/annox/tree/1.0.2#readme
  [22]: https://github.com/highsource/jaxb2-basics/tree/0.12.0#readme
  [23]: https://github.com/highsource/maven-jaxb2-plugin/tree/0.14.0#readme
  [24]: https://github.com/highsource/jaxb2-annotate-plugin/tree/1.1.0#readme
  [25]: https://github.com/highsource/hyperjaxb3/tree/0.6.2#readme
  [31]: https://github.com/patrodyne/hisrc-basicjaxb-annox/releases/tag/2.1.0
  [32]: https://github.com/patrodyne/hisrc-basicjaxb-annox/releases/tag/2.1.1
  [40]: https://javadoc.io/doc/org.patrodyne.jvnet/hisrc-basicjaxb-annox-parser/latest/index.html

