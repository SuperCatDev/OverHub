package com.sc.overhub.entity

abstract class WikiHeroSkillEntity(
    val type: String) {

    class TYPE {
        companion object {
            const val MAIN = "main"
            const val EXTRA = "extra"
        }
    }

}
data class WikiHeroSkillMainEntity(
    val title: String = "",
    val description: String = "",
    val image: String = ""
): WikiHeroSkillEntity(type = TYPE.MAIN)

data class WikiHeroSkillExtra(
    val extraTitle: String = "",
    val extraDescription: String = ""
) : WikiHeroSkillEntity(type = TYPE.EXTRA)