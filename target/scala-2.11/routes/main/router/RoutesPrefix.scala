
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/knoldus/workspace/play-basic-authetication/conf/routes
// @DATE:Tue Jul 19 11:05:18 IST 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
