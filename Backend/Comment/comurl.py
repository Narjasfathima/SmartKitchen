from django.conf.urls import url
from Comment import views


urlpatterns = [
    url('^up/',views.simple_upload),
    url(r'^android/',views.Comment_view.as_view()),
    url(r'^android1/',views.CookVideo_view.as_view()),
    url(r'^android2/', views.video_view.as_view()),
    url(r'^android3/', views.comment_view.as_view()),
    # url(r'^req/',views.req),

]