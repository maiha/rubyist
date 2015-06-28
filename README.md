[![Build Status](https://travis-ci.org/maiha/rubyist.svg?branch=master)](https://travis-ci.org/maiha/rubyist)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/sc.ala/rubyist_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/sc.ala/rubyist_2.11)

rubyist
=======

  A scala goodies for rubyist.


Features
========

  This provides following features those are well-known to rubyist.

  * using
  * Object#tap
  * Int#times
  * rescue
  * md5
  * Pathname#{read,write}
  * Hashname
  * Host,CurrentHost
  * String#chomp
  * Tempfile
  * NKF


Usage
=====

#### using

```scala
import sc.ala.rubyist.Using._
import java.io.ByteArrayOutputStream
using(new ByteArrayOutputStream) { out =>
  ...
}
// out.close() will be automatically called
```

#### Object#tap

```scala
import sc.ala.rubyist.Tap._
val props = new java.util.Properties().tap{_.put("port", "80")}
// => java.util.Properties = {port=80}
```

#### Int#times

```scala
import sc.ala.rubyist.Times._
3 times { println("ok") }
```

#### rescue

```scala
import sc.ala.rubyist.Rescue._
val num = "1".toInt           // => 1
val num = "x".toInt           // java.lang.NumberFormatException
val num = "x".toInt rescue 0  // => 0
```

#### md5

```scala
import sc.ala.rubyist.Digest
Digest.MD5.hexdigest("ruby") // => "58e53d1324eef6265fdb97b08ed9aadf"
```

#### Pathname

```scala
import sc.ala.rubyist.Pathname
val log = Pathname("log/langs.txt")
log.exists           // => false
log.parent.mkpath
log.write("Ruby\nScala")
log.exists           // => true
log.read()           // => "Ruby\nScala"
log.readlines()      // => List("Ruby", "Scala")

Pathname("hello.txt.sjis", "Shift_JIS").read  // with charset
```

#### Hashname

```scala
import sc.ala.rubyist.Hashname
val hash = Hashname("data/users/910.xml")
hash.path         // => "data/users/e/20/5ee/e205ee2a5de471a70c1fd1b46033a75f/910.xml"
hash.write("...") // write given string into above file
```

#### Host,CurrentHost

```scala
import sc.ala.rubyist.CurrentHost
CurrentHost.name     // => moa
CurrentHost.address  // => 192.168.1.5
```

#### String#chomp

```scala
import sc.ala.rubyist.String._
"abc\n".chomp  // => "abc"
```

#### Tempfile

```scala
import sc.ala.rubyist.Tempfile
Tempfile("foo"){ tmp:Pathname =>
  tmp.write("hello")
}
```

#### NKF

- This module directly invokes "nkf" external program, so it should be installed.

```scala
import sc.ala.rubyist.NKF
val utf8_text = NKF.nkf("-w", sjis_text)
```

Required
========

- tested on scala 2.11.7

Authors: maiha@wota.jp
Links: http://github.com/maiha/rubyist (forked from s21g/rubyist)
