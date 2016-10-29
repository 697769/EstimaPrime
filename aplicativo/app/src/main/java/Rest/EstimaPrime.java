package rest;

public interface EstimaPrime {

    @retrofit2.http.GET("/AcessoService/{email}/{password}")
    public void getEmpresas(@retrofit2.http.Path("email") String email,@retrofit2.http.Path("password") String password,
                        retrofit2.Callback<modelo.Enterprise> response);

    //ping
    @retrofit2.http.GET("/ping")
    public void ping(retrofit2.Callback<String> response);

    //ping
    @retrofit2.http.GET("/ping")
    public retrofit2.Call<okhttp3.ResponseBody> ping2();
}
