// Eclipse plugin no longer included here.
//
// If you want it, create ~/.sbt/plugins/eclipse.sbt
// And put this inside:
//
// addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.1")

resolvers += "Templemore Repository" at "http://templemore.co.uk/repo"

addSbtPlugin("templemore" % "sbt-cucumber-plugin" % "0.7.2")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.9.0")
