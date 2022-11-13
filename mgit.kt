import java.math.BigInteger
import java.io.File
import java.security.MessageDigest

fun String.sha1(): String {
    val md = MessageDigest.getInstance("SHA-1")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

fun main(args: Array<String>){
    val directoryPath = File("./.mgit/objects")
    val command = args[0]
    if(command == "init"){
        if(directoryPath.mkdirs()){
            println("folder created.")
        }else{
            println("folder already exists")
        }
    }else{
        if(!directoryPath.isDirectory()){
            println("Run mgit init first.")
        }else if(command == "hash-object"){
            val filePath = args[1]
            val file = File(filePath)

            if(!file.exists() || file.isDirectory()){
                println("Not a valid filepath.")
            }else{
                val content = file.readText()
                val hash = content.sha1();
                println(hash)               
                val obj = File("./.mgit/objects/${hash}")
                obj.writeText(content)
            }
        }else if( command == "cat-file"){
            val OID = args[1]
            val obj = File("./.mgit/objects/${OID}")

            if(!obj.exists() || obj.isDirectory()){
                println("No such object exists.")
            }else{
                val content = obj.readText()
                println(content)
            }
        }
    }

}
