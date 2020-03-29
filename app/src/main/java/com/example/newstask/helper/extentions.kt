
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.RemoteException
import android.util.AndroidException
import android.widget.Toast
import com.example.newstask.network.model.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.*

fun <T> Observable<Response<T>>.runOnMain(runLoad:()->Unit): Observable<Response<T>> = subscribeOn(
    Schedulers.io())
    .doOnSubscribe {
        runLoad()
    }
    .observeOn(AndroidSchedulers.mainThread())



fun <T> Observable<Response<T>>
        .subscribeWithLoading(onLoad: () -> Unit,onSuccess: (T) -> Unit, onError: (throwable:Throwable) -> Unit = { throw  it}, onComplete:()->Unit
) : Disposable {
    return runOnMain{
        // states!!.postValue(Resource.loading())
    }
        .doOnSubscribe {
            onLoad()
        }
        .subscribe({
            val baseResponse = it.body() as BaseResponse
            if (it.isSuccessful && baseResponse.state=="ok") {
                onSuccess(it.body()!!)
            }
            else{
                                Throwable(baseResponse.msg).handleException().also {msg-> onError(Throwable(msg)) }

            }
        },{
            it.handleException().also {
                    msg-> onError(Throwable(msg))
            }
        },{
            onComplete()
        }
        )
}

fun Throwable.handleException():String{
    return if (this is HttpException || this is AndroidException ||this is RemoteException || this is BindException||this is PortUnreachableException||this is SocketTimeoutException || this is UnknownServiceException ||this is UnknownHostException || this is IOException ||this is ConnectException || this is NoRouteToHostException){
        "error connection"
    }
    else{
        this.message!!
    }
}


fun Context.openUrl(url: String) {
    try {


        val i = Intent(Intent.ACTION_VIEW)
        if (i.resolveActivity(packageManager) != null) {

            i.data = Uri.parse(url)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        } else {
            Toast.makeText(this, "its empty", Toast.LENGTH_SHORT).show()
        }
    } catch (ex: Exception) {
        Toast.makeText(this, "error url", Toast.LENGTH_SHORT).show()
        ex.printStackTrace()
    }

}
