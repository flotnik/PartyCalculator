package com.partycalc.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ActivePartiesDAO {
    @Insert
    fun insert(vararg item: ActiveParties): LongArray

    @Update
    fun update(vararg item: ActiveParties?)

    @Delete
    fun delete(vararg item: ActiveParties?)

    @Query("SELECT participant.* from activeparties " +
            "LEFT JOIN participant ON participant.id=activeparties.participantId " +
            "where partyId=:partyId")
    fun getParticipantsForParty(partyId: Int): LiveData<List<Participant>>

    @Query("delete from ActiveParties where participantId=:participantId and partyId=:partyId")
    fun removeParticipantFromParty(partyId: Int, participantId: Int)

    @Query("SELECT contrib_comment, contrib_amount from ActiveParties where partyId=:partyId and participantId=:participantId")
    fun getContributions(partyId: Int, participantId: Int): LiveData<List<Contribution>>
}