import { Pipe, PipeTransform } from '@angular/core';
import { TranslateService } from '../services/translate.service';

@Pipe({
  name: 'translate',
  pure: false
})
export class TranslatePipe implements PipeTransform {

  constructor(private translateService: TranslateService) {}

 
  transform(value: string, lang?: string): string {
    if (lang && lang !== this.translateService.currentLanguageValue) {
      this.translateService.setLanguage(lang);
    }

    const currentTranslations = this.translateService.currentTranslations;
    const translatedValue = currentTranslations ? currentTranslations[value] : undefined;

    return translatedValue !== undefined ? translatedValue : value;
  }
}
