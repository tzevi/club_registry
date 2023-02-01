from django.urls import path
from .views import *

app_name='gga'

urlpatterns = [
    path('applications', ClubsView.as_view(), name='applications'),
    path('applications/<str:taxNo>', ActivateClubView, name='activate_club')
]