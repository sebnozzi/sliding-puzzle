resolvers += Resolver.url("scala-js-releases",
    url("http://repo.scala-js.org/repo/releases/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("scala-js-snapshots",
    url("http://repo.scala-js.org/repo/snapshots/"))(Resolver.ivyStylePatterns)

addSbtPlugin("org.scala-lang.modules.scalajs" % "scalajs-sbt-plugin" % "0.2.1")

addSbtPlugin("com.sqality.scct" % "sbt-scct" % "0.2.2")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.10.1")
