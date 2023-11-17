from django.conf.urls import url
from Meal_Calender import views


urlpatterns = [
    url(r'^android/', views.Mcalender_view.as_view()),
    url(r'^android2/', views.Noti.as_view()),
    # url(r'^home/',views.home),
    # url(r'^req/',views.req),
    # url(r'^mng', views.viewnurs),
    # url(r'^deluser/(?P<nuid>\w+)', views.deluser, name="deluser1"),

]