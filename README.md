FRANCAIS

Ayant besoin d'un extracteur d'archive zip / rar / 7z pour un projet personnel sur la plateforme Android 2.2, je vous partage la librairie que j'ai créée afin de répondre à mes besoins. Je la distribue sous la license LGPL 2.1

J'utilise ces projets dans le code de ma libraire :
j7zip  - http://sourceforge.net/projects/p7zip/files/J7Zip/
junrar - https://github.com/edmund-wagner/junrar

Exemple :
try {
	ExtractFile ef = new ExtractFile(Context app, <fichier archive>, <Dossier d'extraction>);
	ef.exec();
} catch (IOException e) {
	Log.e("Extract error", e.getMessage());
}

ENGLISH

Needing a lib for extract archive file (zip / rar / 7z) for a personal project on the Android platform 2.2, I created and share my personnal library. My library is share in the LGPL 2.1

I'm using these project for coding my library :
j7zip  - http://sourceforge.net/projects/p7zip/files/J7Zip/
junrar - https://github.com/edmund-wagner/junrar

Sample : 
try {
	ExtractFile ef = new ExtractFile(context app, <archive file>, <output directory>);
	ef.exec();
} catch (IOException e) {
	Log.e("Extract error", e.getMessage());
}
