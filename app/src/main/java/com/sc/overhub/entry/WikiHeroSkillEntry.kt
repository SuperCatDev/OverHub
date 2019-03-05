package com.sc.overhub.entry

abstract class WikiHeroSkillEntry(
    val type: String) {

    class TYPE {
        companion object {
            const val MAIN = "main"
            const val EXTRA = "extra"
        }
    }

}
data class WikiHeroSkillMainEntry(
    val title: String = "",
    val description: String = "",
    val image: String = ""
): WikiHeroSkillEntry(type = TYPE.MAIN)

data class WikiHeroSkillExtra(
    val extraTitle: String = "",
    val extraDescription: String = ""
) : WikiHeroSkillEntry(type = TYPE.EXTRA)