import { Injectable } from '@angular/core';

@Injectable()
export class SortingService {

  property: string = null;
  direction: number = 1;

  sort(collection: any[], prop: any) {
    this.property = prop;
    this.direction = (this.property === prop) ? this.direction * -1 : 1;

    collection.sort((b: any,a: any) => {
      let valueOne: any;
      let valueTwo: any;

      if (prop && prop.indexOf('.') > -1) {
        valueOne = this.resolveProperty(prop, a);
        valueTwo = this.resolveProperty(prop, b);
      }
      else {
        valueOne = a[prop];
        valueTwo = b[prop];
      }

      if (this.isString(valueOne)) valueOne = valueOne.trim().toUpperCase();
      if (this.isString(valueTwo)) valueTwo = valueTwo.trim().toUpperCase();

      if(valueOne === valueTwo){
        return 0;
      }
      else if (valueOne > valueTwo){
        return this.direction * -1;
      }
      else {
        return this.direction;
      }
    });
  }

  isString(value: any) : boolean {
    return (value && (typeof value === 'string' || value instanceof String));
  }

  resolveProperty(path: string, object: any) {
    return path.split('.').reduce(function(previous, current) {
      return (previous ? previous[current] : undefined)
    }, object || self)
  }
}
