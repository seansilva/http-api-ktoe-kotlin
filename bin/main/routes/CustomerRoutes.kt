package routes
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import models.customerStorage

fun Route.customerRouting(){
    route("/customer"){
        get{
            if (customerStorage.isNotEmpty()){
                call.respond(customerStorage)
            }else{
                call.respondText ("No customers found",status = HttpStatusCode.NotFound)
            }
        }
        get("{id}"){
            val  id = call.parameters["id"] ?: return@get call.respondText(
                "Missing of malformed id",
                status = HttpStatusCode.BadRequest
            )
            val customer = customerStorage.find{ it.id == id}?:return@get call.respondText(
                "No customer with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(customer)
        }
        post {  }
        delete("{id}") {
        }
    }
}