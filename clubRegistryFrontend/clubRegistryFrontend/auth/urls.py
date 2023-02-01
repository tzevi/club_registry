from django.urls import path, include
from .views import *

app_name='auth'
urlpatterns = [
    path('', SigninView.as_view(), name='login'),
    path('signup', SignupView.as_view(), name='signup'),
    path('logout', LogoutView, name='logout'),
   
]
