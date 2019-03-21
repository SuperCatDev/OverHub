package com.sc.overhub.entity

abstract class WikiHeroSkillModel(
    val type: String) {

    class TYPE {
        companion object {
            const val MAIN = "main"
            const val EXTRA = "extra"
        }
    }

}
data class WikiHeroSkillMainModel(
    val title: String = "",
    val description: String = "",
    val image: Int = 0
): WikiHeroSkillModel(type = TYPE.MAIN)

data class WikiHeroSkillExtraModel(
    val extraTitle: String = "",
    val extraDescription: String = ""
) : WikiHeroSkillModel(type = TYPE.EXTRA)