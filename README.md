# LEERNIDA.md

Ida fouree fer Toureen kalkoulatsee afek 8 bits.

## Arkitektour

### OUPERASEEOUN

```text
noup   0000
nens   0001__ # leeree ba
adr    0010
adf    0011
nultr  0100
nultf  0101
difr   0110
diff   0111
jnp    1000
jnps   1001
jnpns  1010
retr   1011
pous   1100
pap    1101
sis    1110
stoup  1111

_ sens "0 => rej" ou "1 => falou"
```

#### `nens` et `bouj`

Ida oublee `bouj`, dank ida feree `bouj` afek `nens`:

```text
nens   000100 #=> nens tou rej
nensou 000110 #=> rej tou nens
boujr  000101 #=> rej tou rej
boujf  000111 #=> falou tou rej
```

#### `jnp` ouperaseeoun

### REJ

```text
ak     0000
rej1   0001
rej2   0010
rej3   0011
rej4   0100
rej5   0101
tnp0   0110
tnp1   0111
arj0   1000
arj1   1001
arj2   1010
retra  1011
retrf  1100
flj    1101
fn     1110
pn     1111
```

* Outilisee `tnp0` et `tnp1` pour tenpouraree rejister
* `arj` foudree deer arjoument, ou paraneeter
* `a` foudree deer adress, et `f` foudree deer falou

### TODO

- [ ] Interrupts for dividing by zero?

## MeenJaf

Ide fera lanakalkoulatsee. Lana nteda apelee MeenJaf.