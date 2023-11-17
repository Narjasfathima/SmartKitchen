from django.conf.urls import url
from Feedback import views


urlpatterns = [
    url(r'^add_feed/',views.add_feed),
    url(r'^view_feed/',views.view_feed),
    # url(r'^mng', views.viewnurs),
    url(r'^read_feed/(?P<feed_id>\w+)', views.read_feed, name="readfeed"),
    url(r'^delete_feed/(?P<feed_id>\w+)', views.delete_feed, name="dltfeed"),

    url(r'^android/', views.Feedback_view.as_view()),
    url(r'^android1/', views.Feedback_view.as_view()),
    url(r'^android11/', views.Tech_view.as_view()),


]