OPML Library for Java
==================

A Java library representing an [OPML][opml] file. It provides an object model
for basic OPML files and a reader to load an object from a file.

There are a number of outline types and the following are supported by this
library:

- Text
- Link
- RSS

New outline types can be added by extending the Outline class in the same
manner that Link and RSS have. There is an OutlineFactory through which Outline
types can be registed which will result in them being created during a read.

I am using this library to tinker with lists of RSS feeds now that Google
Reader is finished.

## Basic Usage

```java
   Opml opml = new OpmlReader(new File(fileName)).read();

```

More examples using the library can be seen in the test suite.

## Building the Java Wordpress API


A Maven POM is provided with the library.

To build and install the library:

```shell
  mvn install
```

## Using the library in your project

The release versions of the library are deployed to Github which means you will
need to add a repository to your Maven POM:

```xml
  <repositories>
    <repository>
      <id>opml-core-mvn-repo</id>
      <url>https://raw.github.com/ashri/opml-core/mvn-repo/</url>
      <snapshots>
        <enabled>true</enabled>
        <updatePolicy>always</updatePolicy>
      </snapshots>
    </repository>
  </repositories>
```

You can then reference the library as a dependency using the following:

```xml
  <dependencies>
    <dependency>
      <groupId>com.threeheadedmonkey.opml</groupId>
      <artifactId>opml-core</artifactId>
      <version>1.0.0</version>
    </dependency>
  </dependencies>
```

## Copyright

Copyright (c) 2013 Ashley Richardson. See [LICENSE][] for details.

[opml]: http://opml.org
[license]: https://github.com/ashri/opml-core/blob/master/LICENSE.md

