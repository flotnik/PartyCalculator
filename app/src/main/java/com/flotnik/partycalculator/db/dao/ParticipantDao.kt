package com.flotnik.partycalculator.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.flotnik.partycalculator.db.entities.Participant

@Dao
interface ParticipantDao {

    @Query("SELECT * FROM participant WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Participant>

    @Query("SELECT * FROM participant WHERE name LIKE :name")
    fun findByName(name: String): Participant

    @Insert
    fun insert(party: Participant): Long

    @Delete
    fun delete(party: Participant)

    @Query(
        """
         SELECT participant.id, participant.name 
         FROM participant 
         LEFT JOIN participant2party on participant.id = participant2party.participant_id 
         LEFT JOIN party on party.id = participant2party.party_id
         WHERE party.id = :partyId 
    """
    )
    fun getPartyParticipants(partyId: Int): LiveData<List<Participant>>

    @Query("DELETE FROM participant2party where participant_id = :participantId and party_id = :partyId")
    fun deleteParticipantFromParty(participantId: Int, partyId: Int)

    @Query("INSERT INTO participant2party values (:partyId, :participantId)")
    fun addParticipantToParty(participantId: Int, partyId: Int)
}