resolvers += Resolver.url("scala-js-releases",
    url("http://repo.scala-js.org/repo/releases/"))(Resolver.ivyStylePatterns)

addSbtPlugin("org.scala-lang.modules.scalajs" % "scalajs-sbt-plugin" % "0.1")

addSbtPlugin("com.sqality.scct" % "sbt-scct" % "0.2.2")
