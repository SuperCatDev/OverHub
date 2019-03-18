package com.sc.overhub.data

import androidx.room.Dao
import androidx.room.Query
import com.sc.overhub.data.wiki.hero.WikiHeroEntity
import com.sc.overhub.data.wiki.hero.WikiHeroSkillEntity
import com.sc.overhub.data.wiki.hero.WikiHeroSkillExtraEntity
import com.sc.overhub.data.wiki.hero.WikiHeroTipEntity
import com.sc.overhub.model.WikiHeroForList

@Dao
interface WikiHeroDao {

    @Query("SELECT wiki_hero.id, wiki_hero.name, wiki_hero_role.role, wiki_hero.complexity FROM wiki_hero INNER JOIN wiki_hero_role ON wiki_hero.id_role = wiki_hero_role.id")
    suspend fun getHeroesForList(): List<WikiHeroForList>

    @Query("SELECT * from wiki_hero WHERE id = :heroID ")
    suspend fun getHeroById(heroID: Long): WikiHeroEntity

    @Query("SELECT * from wiki_hero_skill WHERE hero_id= :heroID")
    suspend fun getHeroSkillsById(heroID: Long): List<WikiHeroSkillEntity>

    @Query("SELECT * from wiki_hero_skill_extra WHERE id_skill= :skillId")
    suspend fun getHeroSkillsExtraById(skillId: Long): List<WikiHeroSkillExtraEntity>

    @Query("SELECT * from wiki_hero_tip WHERE hero_id= :heroID")
    suspend fun getHeroTipsById(heroID: Long): List<WikiHeroTipEntity>

}