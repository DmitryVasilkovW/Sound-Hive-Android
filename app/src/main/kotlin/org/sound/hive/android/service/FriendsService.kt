package org.sound.hive.android.service

import org.sound.hive.android.model.Friend

/**
 * Service interface for friend-related operations.
 * This service abstracts the data source (API or database) from the rest of the application.
 */
interface FriendsService {
    /**
     * Get all friends of the current user.
     *
     * @return A list of all friends.
     */
    suspend fun getAllFriends(): List<Friend>
    
    /**
     * Get a friend by ID.
     *
     * @param friendId The ID of the friend to retrieve.
     * @return The friend if found, null otherwise.
     */
    suspend fun getFriendById(friendId: String): Friend?
    
    /**
     * Add a new friend.
     *
     * @param friend The friend to add.
     * @return True if the friend was added successfully, false otherwise.
     */
    suspend fun addFriend(friend: Friend): Boolean
    
    /**
     * Remove a friend.
     *
     * @param friendId The ID of the friend to remove.
     * @return True if the friend was removed successfully, false otherwise.
     */
    suspend fun removeFriend(friendId: String): Boolean
    
    /**
     * Get friends with similar music taste.
     *
     * @return A list of friends with similar music taste.
     */
    suspend fun getFriendsWithSimilarTaste(): List<Friend>
}