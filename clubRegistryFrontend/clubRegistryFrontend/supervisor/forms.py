from django import forms

TYPE_CHOICES = [
    ('ROOM', 'room'),
    ('SHOP', 'shop')

]

class CommaSeparatedField(forms.CharField):

    def to_python(self, value):
        if value in self.empty_values:
            return self.empty_value
        value = str(value).split(',')
        if self.strip:
            value = [s.strip() for s in value]
        return value

    def prepare_value(self, value):
        if value is None:
            return None
        return ', '.join([str(s) for s in value])

class AddClubForm(forms.Form):
    taxNo=forms.CharField(required=True,min_length=12,max_length=12,label='Tax Number')
    clubName=forms.CharField(required=True,min_length=3,max_length=80,label='Club Name')
    teamName=forms.CharField(required=True,min_length=3,max_length=80,label='Team Name')
    members = CommaSeparatedField(label='Input member names (separate with commas): ')

class EditClubForm(forms.Form):
    taxNo=forms.CharField( widget=forms.HiddenInput())
    clubName=forms.CharField(required=True,min_length=3,max_length=80,label='Club Name')
    teamName=forms.CharField(required=True,min_length=3,max_length=80,label='Team Name')
    active=forms.BooleanField( widget=forms.HiddenInput())
    members = CommaSeparatedField(label='Input member names (separate with commas): ')

class AddShopForm(forms.Form):
    city=forms.CharField(required=True,min_length=2,max_length=80,label='City')
    address=forms.CharField(required=True,min_length=3,max_length=80,label='Address')
    shopType=forms.ChoiceField(choices=TYPE_CHOICES,label='Shop Type')

class EditShopForm(forms.Form):
    id=forms.IntegerField( widget=forms.HiddenInput())
    city=forms.CharField(required=True,min_length=2,max_length=80,label='City')
    address=forms.CharField(required=True,min_length=3,max_length=80,label='Address')
    shopType=forms.ChoiceField(choices=TYPE_CHOICES,label='Shop Type')
    active=forms.BooleanField(required=False, widget=forms.HiddenInput())
