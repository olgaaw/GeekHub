import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, filter, map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TranslateService {
  private _translations = new BehaviorSubject<any>({});
  public translations$: Observable<any> = this._translations.asObservable();

  private _currentLanguage = new BehaviorSubject<string>('es');
  public currentLanguage$: Observable<string> = this._currentLanguage.asObservable();

  constructor(private http: HttpClient) {
    const savedLang = localStorage.getItem('language') || 'es';
    this._currentLanguage.next(savedLang);
    this.loadTranslations(savedLang);
  }


  private loadTranslations(lang: string): void {
    this.http.get(`/assets/i18n/translations.json`).subscribe({
      next: (data: any) => {
        if (data && data[lang]) {
          this._translations.next(data[lang]);
        } else {
          this._translations.next({});
        }
      },
      error: () => {
        this._translations.next({});
      }
    });
  }


  setLanguage(lang: string): void {
    if (this._currentLanguage.getValue() !== lang) {
      this._currentLanguage.next(lang);
      localStorage.setItem('language', lang);
      this.loadTranslations(lang);
    }
  }


  get currentTranslations(): any {
    return this._translations.getValue();
  }

  get currentLanguageValue(): string {
    return this._currentLanguage.getValue();
  }


  getTranslatedString(key: string): Observable<string> {
    return this.translations$.pipe(
      filter(translations => Object.keys(translations).length > 0),
      map(translations => translations[key] || key)
    );
  }
}