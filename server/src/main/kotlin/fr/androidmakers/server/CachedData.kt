package fr.androidmakers.server

import fr.androidmakers.server.model.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream


object CachedData {
  private lateinit var _rooms: List<Room>
  private lateinit var _sessions: List<Session>
  private lateinit var _speakers: List<Speaker>

  private val json = Json {
    ignoreUnknownKeys = true
  }

  val rooms: List<Room>
    get() = _rooms
  val sessions: List<Session>
    get() = _sessions
  val speakers: List<Speaker>
    get() = _speakers

  @OptIn(ExperimentalSerializationApi::class)
  fun initialize() {
    _rooms = this.javaClass.classLoader.getResourceAsStream("rooms.json").use {
      json.decodeFromStream<JsonRoomData>(it)
    }.map {
      Room(
        id = it.key,
        capacity = it.value.capacity,
        name = it.value.name,
        level = it.value.level,
      )
    }

    _sessions = this.javaClass.classLoader.getResourceAsStream("sessions.json").use {
      json.decodeFromStream<JsonSessionData>(it)
    }.map {
      Session(
        id = it.key,
        title = it.value.title,
        complexity = it.value.complexity,
        description = it.value.description,
        feedback = it.value.feedback,
        icon = it.value.icon,
        language = it.value.language,
        platformUrl = it.value.platformUrl,
        slido = it.value.slido,
        speakerIds = it.value.speakers.toSet(),
        tags = it.value.tags,
      )
    }

    _speakers = this.javaClass.classLoader.getResourceAsStream("speakers.json").use {
      json.decodeFromStream<JsonSpeakerData>(it)
    }.map {
      Speaker(
        id = it.key,
        name = it.value.name,
        bio = it.value.bio,
        shortBio = it.value.shortBio,
        photo = it.value.photo,
        photoUrl = it.value.photoUrl,
        company = it.value.company,
        companyLogo = it.value.companyLogo,
        country = it.value.country,
        featured = it.value.featured,
        order = it.value.order,
        socials = it.value.socials.map { jsonSocial ->
          Social(
            icon = jsonSocial.icon,
            name = jsonSocial.name,
            link = jsonSocial.link
          )
        }
      )
    }
  }
}