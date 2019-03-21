package com.sc.overhub.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sc.overhub.data.wiki.WikiHero
import com.sc.overhub.data.wiki.hero.*
import com.sc.overhub.data.wiki.WikiHeroForList

@Dao
interface WikiHeroDao {

    @Query("SELECT wiki_hero.id, wiki_hero.name, wiki_hero_role.role, wiki_hero.complexity, wiki_hero.res_uri FROM wiki_hero INNER JOIN wiki_hero_role ON wiki_hero.id_role = wiki_hero_role.id")
    suspend fun getHeroesForList(): List<WikiHeroForList>

    @Query("SELECT wiki_hero.id, wiki_hero.name, wiki_hero.description, wiki_hero.res_uri, wiki_hero.complexity, wiki_hero_role.role FROM wiki_hero INNER JOIN wiki_hero_role ON wiki_hero.id_role = wiki_hero_role.id WHERE wiki_hero.id = :heroID")
    suspend fun getHeroById(heroID: Long): WikiHero

    @Query("SELECT * FROM wiki_hero_overview WHERE id_hero = :heroID")
    suspend fun getHeroOverView(heroID: Long): List<WikiHeroOverviewEntity>

    @Query("SELECT * from wiki_hero_skill WHERE hero_id= :heroID")
    suspend fun getHeroSkillsById(heroID: Long): List<WikiHeroSkillEntity>

    @Query("SELECT * from wiki_hero_skill_extra WHERE id_skill= :skillId")
    suspend fun getHeroSkillsExtraById(skillId: Long): List<WikiHeroSkillExtraEntity>

    @Query("SELECT * from wiki_hero_tip WHERE hero_id= :heroID")
    suspend fun getHeroTipsById(heroID: Long): List<WikiHeroTipEntity>

    @Insert
    fun I_insertHero(data: List<WikiHeroEntity>)

    @Insert
    fun I_insertHeroOverview(data: List<WikiHeroOverviewEntity>)

    @Insert
    fun I_insertHeroRole(data: List<WikiHeroRoleEntity>)

    @Insert
    fun I_insertSkill(data: List<WikiHeroSkillEntity>)

    @Insert
    fun I_insertSkillExtra(data: List<WikiHeroSkillExtraEntity>)

    @Insert
    fun I_insertTip(data: List<WikiHeroTipEntity>)

}