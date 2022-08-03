import kotlinx.html.*

object Utils {

  fun textItem(title: String): LI.() -> Unit = {
    +title
  }

  fun linkItem(title: String, url: String, newTab: Boolean = true): LI.() -> Unit = {
    a {
      +title
      href = url
      if (newTab)
        target = "_blank"
    }
  }

}