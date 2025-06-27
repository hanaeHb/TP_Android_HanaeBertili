package com.example.ecommerceapp.data.Repository

import androidx.compose.runtime.mutableStateListOf
import com.example.ecommerceapp.data.Entities.Client

object ClientRepository {

    private val clients = mutableMapOf<String, Client>()
    private val clientList = mutableStateListOf<Client>()

    fun addClient(client: Client) {
        clients[client.email] = client
        if (!clientList.any { it.email == client.email }) {
            clientList.add(client)
        }
    }

    fun getClientByEmail(email: String): Client? {
        return clients[email]
    }

    fun getAllClients(): List<Client> = clients.values.toList()

    fun deleteClient(client: Client) {
        clients.remove(client.email)
        clientList.removeIf { it.email == client.email }
    }

    fun updateClient(client: Client) {
        clients[client.email] = client
        val index = clientList.indexOfFirst { it.email == client.email }
        if (index != -1) {
            clientList[index] = client
        } else {
            clientList.add(client)
        }
    }
}