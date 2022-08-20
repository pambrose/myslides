import kotlinx.html.*

object Utils {

  fun textItem(title: String): LI.() -> Unit = {
    +title
  }

  fun linkItem(title: String, url: String, newTab: Boolean = true): LI.() -> Unit = { alink(title, url, newTab) }

  fun FlowOrInteractiveOrPhrasingContent.alink(title: String, url: String, newTab: Boolean = true) =
    a {
      +title
      href = url
      if (newTab)
        target = "_blank"
    }
}