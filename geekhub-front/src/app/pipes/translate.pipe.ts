import { Pipe, PipeTransform } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Pipe({
  name: 'translate',
  pure: false
})
export class TranslatePipe implements PipeTransform {

  private translations: any = {};
  private currentLanguage = 'es';
  
  constructor(private http: HttpClient) {
    this.currentLanguage = localStorage.getItem('language') || 'es';
    this.loadTranslations();
  }

  private loadTranslations() {
    this.http.get('/assets/i18n/translations.json').subscribe(data => {
      this.translations = data;
    });
  }

  transform(value: string, lang?: string): string {
    if (lang && lang !== this.currentLanguage) {
      this.currentLanguage = lang;
      localStorage.setItem('language', lang);
      this.loadTranslations();
      return '';
    }
    return this.translations[this.currentLanguage][value] || value;
  }
}
