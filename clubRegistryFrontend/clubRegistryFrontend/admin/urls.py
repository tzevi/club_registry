from django.urls import path, include
from .views import *

app_name='admin'

urlpatterns = [
    path('users', UsersView.as_view(), name='users'),
    path('users/add', AddUserView.as_view(), name='add_user'),
    path('users/<str:username>/edit', EditUserView.as_view(), name='edit_user'),
    path('users/<str:username>/delete', DeleteUserView, name='delete_user'),
]