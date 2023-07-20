//package ru.store.store_rest.grpc.repository
//
//import org.springframework.stereotype.Repository
//import ru.store.store_rest.config.UserConfig
//import ru.store.store_rest.grpc.mapper.UserGrpcMapperRequest
//import ru.store.store_rest.grpc.mapper.UserGrpcMapperResponse
//import ru.store.store_rest.model.UserDto
//import ru.store.store_user.UserOuterClass.Status
//
//@Repository
//open class UserRepositoryGrpc {
//
//    fun saveUser(user: UserDto): String? {
//        val request = UserGrpcMapperRequest.userMapper(user)
//        val response = UserConfig.getStub().saveUser(request)
//        val result = response.get()
//        return when (result.status) {
//            Status.OK -> result.user.login.value
//            Status.BAD -> "-1"
//            else -> throw Exception("${result.error.class_}: ${result.error.message}")
//        }
//    }
//
//    fun getUserByLogin(login: String): UserDto {
//        val request = UserGrpcMapperRequest.userMapper(UserDto(login))
//        val response = UserConfig.getStub().getUserByLogin(request)
//        val result = response.get()
//        return when (result.status) {
//            Status.OK -> UserGrpcMapperResponse.userMapper(result.user)
//            Status.BAD -> UserDto()
//            else -> throw Exception("${result.error.class_}: ${result.error.message}")
//        }
//    }
//
//    fun delete(login: String): String {
//        val request = UserGrpcMapperRequest.userMapper(UserDto(login))
//        val response = UserConfig.getStub().delete(request)
//        val result = response.get()
//        return when (result.status) {
//            Status.OK -> result.user.login.value
//            Status.BAD -> "-1"
//            else -> throw Exception("${result.error.class_}: ${result.error.message}")
//        }
//    }
//}