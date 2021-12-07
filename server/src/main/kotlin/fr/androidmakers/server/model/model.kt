package fr.androidmakers.server.model

data class Room(
  val id: String,
  val name: String,
  val capacity: Int,
  val level: String
)

data class Session(
  val id: String,
  val title: String,
  val description: String,
  val language: String?,
  val complexity: String?,
  val speakers: List<String>,
  val tags: List<String>,
  val icon: String?,
  val platformUrl: String?,
  val feedback: String?,
  val slido: String?,
)

data class Speaker(
  val id: String,
  val order: Float?,
  val featured: Boolean,
  val name: String,
  val bio: String,
  val shortBio: String,
  val country: String?,
  val companyLogo: String?,
  val company: String?,
  val photo: String,
  val socials: List<Social>,
  val photoUrl: String
)

data class Social(
  val icon: String,
  val link: String,
  val name: String
)
