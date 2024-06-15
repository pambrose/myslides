import com.kslides.atag
import kotlinx.html.LI

object Utils {

  fun textItem(title: String): LI.() -> Unit = {
    +title
  }

  fun linkItem(
    title: String,
    url: String,
    newTab: Boolean = true,
  ): LI.() -> Unit = { atag(title, url, newTab) }
}
