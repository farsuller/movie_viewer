package com.viewer.movieviewer.repository

enum class Status{
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val state: Status, val msg: String){

    companion object{
        val LOADED: NetworkState = NetworkState(Status.SUCCESS, "Success")
        val LOADING: NetworkState = NetworkState(Status.RUNNING, "Running")
        val ERROR: NetworkState = NetworkState(Status.FAILED, "Something went wrong")

    }

}