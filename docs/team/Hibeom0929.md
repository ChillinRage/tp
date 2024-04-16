---
layout: page
title: Yang Heebeom's Project Portfolio Page
---

### Project: NUSContacts

NUSContacts is a desktop app designed for NUS Students, offering a streamlined way to manage their academic contacts with ease.

Given below are my contributions to the project.

### Code contributed
You can find the overall code using this [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=Hibeom0929&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Hibeom0929&tabRepo=AY2324S2-CS2103T-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

### New features
* Added new **role** field to contacts.   
  (Pull request[#30](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/30))
    * What it does: allows the user to save the role of the contacts.
    * Justification: This feature is necessary as our product is for NUS students and showing relationship is one of the important purpose of our product.

### Enhancements to existing features
* `help` command now not only opens the help window box but also listed all existing commands.  
  (Pull request[#84](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/84))
  * **What it does**: Users can find all commands without accessing User Guide by `help` command.
  * **Impact**: Users now find it convenient to access all commands without having to open an external webpage.
  
* User can `find` contacts by `Course`.  
  (Pull request[#95](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/95))
  * **What it does**: Users can use the `find` command with `Course`.
  * **Impact**: Users now can simply find all contacts of a certain `Course`. 

* `Name` field can detect case-insensitive name duplication.  
  (Pull request[#118](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/118))
  * **What it does**: Names with same letter and different cases are also considered as same name. For example, `Heebeom` and `heeBeom` are same names.
  * **Impact**: Users can't add the contacts with same name even their name's case is different.

* User can `find` contacts by `Role`.  
  (Pull request[#132](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/132))
  * **What it does**: Users can use the `find` command with `Role`.
  * **Impact**: Users now can simply find all contacts of a certain `Role`.
  
* Wrote and updated tests for the new features and enhanced features that I made. Also, added more tests and enabled previously disabled tests.
 (e.g.Pull request[#204](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/204))

### Documentation
* User Guide:
  * Added examples of features like bypassing and find with images.
  * Edited all nouns to have consistency.
  * Divided each sections with the line with a link 'Back to the Table of Contents' at the end.
* Developer Guide:
  * Added planned enhancements.
    (Pull request[#201](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/201))

### Community
* PRs reviewed (with non-trivial review comments):
  e.g.[#146](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/146),
  [#197](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/197)
* Locally tested and approved PRs
* Contributed to almost all the discussions in issues. 
  (examples: issues [64](https://github.com/AY2324S2-CS2103T-T11-2/tp/issues/64),
             issues [129](https://github.com/AY2324S2-CS2103T-T11-2/tp/issues/129))

